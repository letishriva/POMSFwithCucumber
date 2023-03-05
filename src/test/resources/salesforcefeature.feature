Feature: login functionality

Background: 
Given Browser is up and running in loginpage

Scenario: tc1 Login error Message
	When I enter wrong username
	And  I click on clear password field
	And  I click on the login button
	Then Error Message should be displayed

	Scenario: tc2 login with correct credentials
	When I enter correct username
	And I enter correct password
	And I click on the login button
	Then I should get the home page
	
	Scenario: tc3 Login with Remember Username
	When I enter correct username
	And I enter correct password
	And I click on the login button
	Then I should get the home page
	When I click on logout
	Then I should get the Login page with correct username
	
	Scenario: tc4a Login with Forgot Password
	When I click on forgot password
	Then SalesForce forgot password page is displayed
	When I enter correct username in forgot password page
	And I click on continue button
	Then Password reset page is displayed

Scenario: tc4b Login with wrong Password
	When I enter wrong username
	And I enter wrong password
	And I click on the login button
	Then Login failed Error Message is displayed
	