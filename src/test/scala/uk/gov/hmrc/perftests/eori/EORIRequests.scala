/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.eori

import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.eori.Requests._

trait EORIRequests {
  self: PerformanceTestRunner with ServicesConfiguration =>

  private val baseUrl: String = baseUrlFor("customs-update-eori-admin-frontend")

  val payload = Map(
    "usersGivenName" -> "",
    "pid" -> "12345",
    "status" -> "true",
    "usersSurname" -> "",
    "emailAddress" -> "",
    "roles" -> "update-enrolment-eori",
  "signature" -> "valid",
  )

  setup("initial-journey", "test ") withRequests(
    getPage("Login Page", true, s"$baseUrl/customs-update-eori-admin-frontend"),
    getPage("Enter PID and Roles", true, s"$baseUrl/customs-update-eori-admin-frontend/stride-idp-stub/sign-in"),
    postPage("Enter PID and Roles", s"$baseUrl/customs-update-eori-admin-frontend", s"$baseUrl/customs-update-eori-admin-frontend", payload),
    // getPage("About the goods", true,s"$baseUrl/advance-valuation-ruling/importGoods")

  )

}
