package retrospective.application

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import retrospective.adapter.InMemoryRetrospectiveRepository
import retrospective.domain.Retrospective
import strikt.api.expectThat
import strikt.assertions.contains
import strikt.assertions.hasSize
import kotlin.test.assertTrue

object RetrospectiveApplicationServiceTest : Spek({
    Feature("회고 기록하기") {
        val applicationService = RetrospectiveApplicationService(InMemoryRetrospectiveRepository())

        Scenario("새로운 회고 기록 추가하기") {
            lateinit var memo: String
            lateinit var loggedRetro: Retrospective
            val userName = "LasWonho"

            Given("올바른 회고 기록이 주어졌을 때") {
                memo = "나는 오늘 빵을 먹었다. 맛있었다. 또 먹어야지~!"
            }

            When("회고를 기록한다면") {
                loggedRetro = applicationService.logRetrospective(userName, memo)
            }

            Then("회고를 찾을 수 있다.") {
                assertTrue(applicationService.findSpecificRetrospective(loggedRetro.id).isLeft())
            }
        }
    }

    Feature("회고 살펴보기") {
        val applicationService = RetrospectiveApplicationService(InMemoryRetrospectiveRepository())
        Scenario("내가 기록한 회고 가져오기") {
            lateinit var memo: String
            lateinit var loggedRetro: Retrospective
            lateinit var allMyRetrospective: List<Retrospective>
            val userName = "LasWonho"

            Given("회고를 이미 저장한 경우") {
                memo = "나는 오늘 빵을 먹었다. 맛있었다. 또 먹어야지~!"
                loggedRetro = applicationService.logRetrospective(userName, memo)
            }

            When("내가 기록한 회고를 가져온다면") {
                allMyRetrospective = applicationService.findMyAllRetrospective(userName)
            }

            Then("회고를 찾을 수 있다.") {
                expectThat(allMyRetrospective)
                        .hasSize(1)
                        .contains(loggedRetro)
            }
        }
    }
})