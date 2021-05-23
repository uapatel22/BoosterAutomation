$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resource/Features/Pledges.feature");
formatter.feature({
  "name": "Pledges",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Validate the Business Leaderboard is sorted highest to lowest for the business pledges",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Pledges"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I open the browser then navigate to the website",
  "keyword": "Given "
});
formatter.match({
  "location": "PledgesStepDefs.java:17"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I validate there are \u00275\u0027 pledges displayed under Our Business Leaderboard",
  "keyword": "When "
});
formatter.match({
  "location": "PledgesStepDefs.java:22"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I validate the pledges are displayed in highest to lowest dollar amount",
  "keyword": "And "
});
formatter.match({
  "location": "PledgesStepDefs.java:27"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I validate if decimals are displayed or not for the pledges below:",
  "rows": [
    {
      "cells": [
        "Pearl",
        "notDisplayed"
      ]
    },
    {
      "cells": [
        "businessnotetest",
        "notDisplayed"
      ]
    },
    {
      "cells": [
        "Businessbetatest",
        "notDisplayed"
      ]
    },
    {
      "cells": [
        "businessleader30",
        "isDisplayed"
      ]
    },
    {
      "cells": [
        "testbusiness",
        "notDisplayed"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "PledgesStepDefs.java:32"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I validate \u00271\u0027 pledge is displayed after clicking on Show more",
  "keyword": "Then "
});
formatter.match({
  "location": "PledgesStepDefs.java:37"
});
formatter.result({
  "status": "passed"
});
formatter.embedding("image/png", "images/4aefde99108040d6893d61879941c72e0.png");
formatter.after({
  "status": "passed"
});
});
