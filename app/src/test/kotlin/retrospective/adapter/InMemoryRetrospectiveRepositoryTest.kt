package retrospective.adapter

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import retrospective.domain.Retrospective

object InMemoryRetrospectiveRepositoryTest: Spek({
    Feature("save retrospective") {
        Scenario("회고 저장하기") {
            lateinit var retro: Retrospective
            val repository = InMemoryRetrospectiveRepository()

            Given("정상적인 회고가 주어졌을 때") {
                retro = Retrospective(0L, "Las", "우유를 먹으면 배가 아파")
            }
            When("회고를 repository 에 저장하는 경우") {
                repository.save(retro)
            }
            Then("repository 에서 저장한 회고를 찾을 수 있다") {
                repository.findById(retro.id)
            }
        }
    }
})
