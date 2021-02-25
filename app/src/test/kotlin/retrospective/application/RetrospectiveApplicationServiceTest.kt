package retrospective.application

import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertNotNull

object RetrospectiveApplicationServiceTest : Spek({
    Feature("회고 기록하기") {

        Scenario("새로운 회고 기록 추가하기") {
            lateinit var memo: String
            val userName = "LasWonho"

            Given("올바른 회고 기록이 주어졌을 때") {
                memo = "나는 오늘 빵을 먹었다. 맛있었다. 또 먹어야지~!"
            }

            When("회고를 기록한다면") {
                loggedRetro = applicationService.logRetrospective(userName, memo)
            }

            Then("회고를 찾을 수 있다.") {
                assertNotNull(applicationService.findSpecificRetrospective(loggedRetro.getId()))
            }
        }
    }
})