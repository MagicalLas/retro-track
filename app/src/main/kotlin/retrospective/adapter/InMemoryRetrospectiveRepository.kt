package retrospective.adapter

import arrow.core.Either
import arrow.core.rightIfNotNull
import retrospective.domain.Retrospective
import retrospective.domain.RetrospectiveNotFoundError
import retrospective.domain.RetrospectiveRepository

class InMemoryRetrospectiveRepository: RetrospectiveRepository {
    private val inMemoryCache: MutableMap<Long, Retrospective> = mutableMapOf()
    private var lastId = 0L

    override fun findById(id: Long): Either<Retrospective, RetrospectiveNotFoundError> {
        return inMemoryCache[id].rightIfNotNull { RetrospectiveNotFoundError(id) }.swap()
    }

    override fun save(retro: Retrospective) {
        inMemoryCache[retro.id] = retro
    }

    override fun findByUserName(userName: String): List<Retrospective> {
        return inMemoryCache.values.filter {
            it.userName == userName
        }
    }

    override fun nextId(): Long {
        return lastId++
    }
}
