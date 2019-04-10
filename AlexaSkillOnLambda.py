
from __future__ import print_function
import json
from botocore.vendored import requests


def build_speechlet_response(output,should_end_session):
    return {
        'outputSpeech': {
            'type': 'PlainText',
            'text': output
        },
        'shouldEndSession': should_end_session
    }

def build_response(session_attributes, speechlet_response):
    return {
        'version': '1.0',
        'sessionAttributes': session_attributes,
        'response': speechlet_response
    }



def get_country_run_response(intent):
    session_attributes = {}
    country_value= intent['slots']['country']['value']
    url = "https://cricket-rest-api.herokuapp.com/batsmen/{0}/runs".format(country_value)
    
    r = requests.get(url)
    
    response = r.json()
    
    player_string = "The player name is " + response['name']
    
    
    speech_output = player_string
    
    should_end_session = False
    return build_response(session_attributes, build_speechlet_response(
         speech_output, should_end_session))


def get_run_response(intent):
    session_attributes = {}
    url = "https://cricket-rest-api.herokuapp.com/batsmen/runs"
    
    r = requests.get(url)
    response = r.json()
   
    player_string = "The player name is " +  response['name']

    speech_output = player_string
    
    should_end_session = False
    return build_response(session_attributes, build_speechlet_response(
        speech_output, should_end_session))


def get_age_response(intent):
    session_attributes = {}
    age_value= intent['slots']['age']['value']

    url = "https://cricket-rest-api.herokuapp.com/batsmen/age/{0}".format(age_value)
    
    r = requests.get(url)
    
    response = r.json()
    
    player_string = ""
    
    
    if len(response) == 0:
        player_string = "There are no players above {0}".format(age_value)
    elif len(response) == 1:
        player_string= "The Player name is " 
        for element in response :
            player_string += element ['name']
    else :
        player_string = "The Player names are "
        for element in response :
            player_string += element['name'] + " "
        
        
    speech_output = player_string
    
    should_end_session = False
    return build_response(session_attributes, build_speechlet_response(
         speech_output, should_end_session))


def get_country_response(intent):
    session_attributes = {}
    country_value= intent['slots']['countries']['value']
    url = "https://cricket-rest-api.herokuapp.com/batsmen/country/{0}".format(country_value)
    
    r = requests.get(url)
    
    player_string = ""
    
    response = r.json()
    
    
    if len(response) == 0:
        player_string= "Sorry! There are no players."
    elif len(response) == 1:
        player_string= "The Player name is " 
        for element in response :
            player_string += element ['name']
    else :
        player_string="The Player names are "
        for element in response :
            player_string += element['name'] + " "
    
    speech_output = player_string
    
    should_end_session = False
    return build_response(session_attributes, build_speechlet_response(
         speech_output, should_end_session))


def get_welcome_response():
    session_attributes = {}
    speech_output = "Welcome to Cricket info"
    
    should_end_session = False
    return build_response(session_attributes, build_speechlet_response(
         speech_output, should_end_session))


def handle_session_end_request():
    speech_output = "Thank you for using Cricket info. " \
                    "Have a nice day! "
    # Setting this to true ends the session and exits the skill.
    should_end_session = True
    return build_response({}, build_speechlet_response(
         speech_output, should_end_session))


def on_session_started(session_started_request, session):
    pass

    

def on_launch(launch_request, session):
    return get_welcome_response()


def on_intent(intent_request, session):
    

    intent = intent_request['intent']
    intent_name = intent_request['intent']['name']

    
    if intent_name == "CountryIntent":
        return get_country_response(intent)
    
    elif intent_name == "AgeIntent":
        return get_age_response(intent)
        
    elif intent_name == "RunIntent":
        return get_run_response(intent)
    elif intent_name == "CountryRunIntent":
        return get_country_run_response(intent)
    elif intent_name == "AMAZON.StopIntent":
        return handle_session_end_request()

def on_session_ended(session_ended_request, session):
    """ Called when the user ends the session.
    Is not called when the skill returns should_end_session=true
    """
    print("on_session_ended requestId=" + session_ended_request['requestId'] +
          ", sessionId=" + session['sessionId'])


# --------------- Main handler ------------------

def lambda_handler(event, context):
    if event['session']['new']:
        on_session_started({'requestId': event['request']['requestId']},
                           event['session'])

    if event['request']['type'] == "LaunchRequest":
        return on_launch(event['request'], event['session'])
    elif event['request']['type'] == "IntentRequest":
        return on_intent(event['request'], event['session'])
    elif event['request']['type'] == "SessionEndedRequest":
        return on_session_ended(event['request'], event['session'])

    
    
  
