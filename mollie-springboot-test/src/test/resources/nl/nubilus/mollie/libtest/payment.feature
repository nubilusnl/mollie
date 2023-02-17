#language: en
Feature: Payment checks
  Background: Test payments with mollie

  Scenario: Starting a new payment from invoice
    When invoice 'invoice.json' is used to send payment
    Then the response should be 'expectedresponsenewpayment.json'
