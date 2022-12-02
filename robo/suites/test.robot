*** Settings ***
Library  Collections
Library  RequestsLibrary

Suite Teardown  Delete All Sessions

*** Test Cases ***
Get Request TestWebsite
    Create Session  getwebsite        https://www.google.com    verify=false
    ${resp}=        GET On Session  getwebsite  /       expected_status=200
    Should Be Equal As Strings      ${resp.status_code}     200


*** Test Cases ***
Example
    FOR    ${domain-name}    IN   google       	google1    
        Log    ${domain-name}
        Create Session  getwebsite        https://www.${domain-name}.com    verify=false
    	${resp}=        GET On Session  getwebsite  /       expected_status=200
        Should Be Equal As Strings      ${resp.status_code}     200

    END
    Log    Outside loop


*** Test Cases ***
Only upper limit
    [Documentation]    Loops over values from 0 to 9.
    FOR    ${index}    IN RANGE    10
        Create Session  getwebsite        https://www.google.com    verify=false
        ${resp}=        GET On Session  getwebsite  /       expected_status=200
        Should Be Equal As Strings      ${resp.status_code}     100
    END