# Alexa Skill for REST Cricket API 

### Repo for Alexa skill that I created for an earlier deployed cricket API that I had developed for learning purposes.

## API endpoints
* `GET` `https://cricket-rest-api.herokuapp.com/`
Homepage for the API
* `GET` `https://cricket-rest-api.herokuapp.com/batsmen/country/{CountryName}`
Returns a JSON object containing list of players from the {CountryName}
* `GET` `https://cricket-rest-api.herokuapp.com/age/{Age}`
Reurns a JSON object containing list of players having age above {Age}
* `GET` `https://cricket-rest-api.herokuapp.com/batsmen/runs`
Returns a JSON object containing the player having maximum runs from all countries
* `GET` `https://cricket-rest-api.herokuapp.com/batsmen/{CountryName}/runs`
Returns a JSON object containing the player having maximum runs from {CountryName}

## Alexa Skill 

Created an Alexa skill to access the API endpoints on voice commands using various Intents.
`CountryIntent` 
> _Alexa, tell me players from {CountryName}_

Recites the names of players from {CountryName}

`AgeIntent` 
> _Alexa, tell me players above {Age}_

Recites the names of players above {Age} from all the countries

`RunIntent` 
> _Alexa, tell me the top scorer_

Recites the name of the player with maximum runs from all countries

`CountryRunIntent` 
> _Alexa, tell me the top scorer from {CountryName}_

Recites the name of the player with maximum runs from {CountryName}

**NOTE** : Since the Alexa skill is not published on the Alexa skill store, any other echo device might not understand the *Open Cricket Info*. However I have provided the code for the AWS Lambda function that serves as the endpoint for this skill in the file `AlexaSkillOnLambda.py`.It will give an idea of how the skill actually works.


