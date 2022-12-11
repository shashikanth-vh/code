*** Settings ***
Library  Collections
Library  RequestsLibrary

Suite Teardown  Delete All Sessions

*** Test Cases ***
Get Request TestWebsite
    Create Session  getwebsite        http://example-app:8084/apps    verify=false
    Sleep  30s
    ${resp}=        GET On Session  getwebsite  /health    expected_status=200
    Should Be Equal As Strings      ${resp.status_code}     200

