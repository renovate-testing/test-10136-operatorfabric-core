Feature: API - creatCardAction


  Background:

    * def signInAsTso = call read('../common/getToken.feature') { username: 'tso1-operator'}
    * def authTokenAsTso = signInAsTso.authToken

  Scenario: Create a card



# Push an action card 

    * def getCard = 
    """
    function() {

      startDate = new Date().valueOf() + 4*60*60*1000;
	  endDate = new Date().valueOf() + 6*60*60*1000;

        var card =
        
        {
            "publisher": "api_test_externalRecipient1",
            "processVersion": "1",
            "process": "processAction",
            "processInstanceId": "processInstanceId1",
            "state": "response_full",
            "startDate": startDate,
            "severity": "ACTION",
            "tags": [
                "tag1"
            ],
            "timeSpans": [
                {
                    "start": startDate
                }
            ],
            "title": {
                "key": "cardFeed.title",
                "parameters": {
                    "title": "Test action - with entity in entitiesAllowedToRespond"
                 }
            },
            "summary": {
                "key": "cardFeed.summary",
                "parameters": {
                "summary": "Test the action with entity in entitiesAllowedToRespond"
                }
            },
            "recipient": {
                "type": "UNION",
                "recipients": [
                    {
                        "type": "GROUP",
                        "identity": "TSO1"
                    }
                ],

            },
            "entityRecipients": ["ENTITY1"],
            "entitiesAllowedToRespond": ["TSO1","ENTITY1"],
            "data": {
                "data1": "data1 content"
            }
        }    

	return JSON.stringify(card);

      }
    """
    * def card = call getCard 


# Push card - card response without entity in entity  in entitiesAllowedToRespond
    Given url opfabPublishCardUrl + 'cards'
    And header Authorization = 'Bearer ' + authTokenAsTso
    And header Content-Type = 'application/json'
    And request card
    When method post
    Then status 201
    And match response.count == 1
    * def statusCode = responseStatus
    * def body = $