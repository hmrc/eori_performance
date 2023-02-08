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

   val baseUrl: String = baseUrlFor("eori-frontend")


  val payload = Map(
  "pid"->"GB123456123456",
  //  "usersGivenName"->"",
//    "usersSurname"->"",
//  "emailAddress"->"",
//    "status"->"true",
//  "signature"-> "valid",
  "roles"->"update-enrolment-eori"
  )

  setup("initial-journey", "test ") withRequests(
    getPage("eori home",true, s"$baseUrl/customs-update-eori-admin-frontend"),
//    getPage("starter checklist",true, s"$baseUrl/advance-valuation-ruling/requiredInformation?csrfToken="+"${csrfToken}"),
    postPage("Enter PID and Roles",s"$baseUrl/customs-update-eori-admin-frontend", s"$baseUrl/customs-update-eori-admin-frontend/update/", payload),
   getPage("EORI Update page", true,s"$baseUrl/customs-update-eori-admin-frontend/update/")

    )
  setup(" eori-api", "verify a Performance  test on Staging ")
    .withRequests(
    getPage("Staging",s"$baseUrl/auth-login-stub/gg-sign-in?continue=%2Fcustoms-update-eori-admin-frontend%2FaccountHome")



    )

}