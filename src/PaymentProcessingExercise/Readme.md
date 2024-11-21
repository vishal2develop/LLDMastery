## Exercise: Adapter Pattern for Payment Processing
### Problem Statement
Imagine you are developing a Payment Processing System that allows users to pay with different payment providers, such as **PayPal** and **Stripe**. The system expects each provider to implement a simple interface with a `pay()` method that accepts an amount. However, each payment provider has its own custom API, which doesn’t match the expected interface.

Your goal is to use the **Adapter Pattern** to integrate both payment providers without modifying their original code, making them compatible with your **Payment Processing System.**

---

### **Step 1: Define the Target Interface**
The PaymentProcessor interface is the Target Interface that your Payment Processing System expects. This interface includes a pay() method.

---

### Step 2: Create the Adaptee Classes
The Adaptees are the PayPal and Stripe classes, representing existing payment providers with incompatible APIs.

**PayPal API (Adaptee)**

The PayPal class has its own method, makePayment(), for processing payments. This method is incompatible with PaymentProcessor.

**Stripe API (Adaptee)**

The Stripe class uses processPayment() instead of makePayment(), which also doesn’t match PaymentProcessor.

---

### Step 3: Implement Adapter Classes
Create adapter classes for both PayPal and Stripe that implement the Target Interface (`PaymentProcessor`) and translate calls from `pay()` to the respective payment methods in PayPal and Stripe.

**PayPalAdapter**

The PayPalAdapter adapts PayPal to PaymentProcessor.

**StripeAdapter**

The StripeAdapter adapts Stripe to PaymentProcessor.

---

### Step 4: Client Code (Payment Processing System)

In the Payment Processing System (the client code), you only interact with PaymentProcessor, allowing you to use PayPalAdapter or StripeAdapter interchangeably without needing to know the details of PayPal or Stripe.

---

### Step 5: Test the Adapter Implementation

In the Main class, create instances of PayPalAdapter and StripeAdapter and use them to process payments through the PaymentSystem.

---

### Explanation
1. **Target Interface**: PaymentProcessor defines the method pay(double amount) that the payment system expects.
2. **Adaptees**: PayPal and Stripe each have their own incompatible APIs (makePayment() and processPayment() respectively).
3. **Adapters**: PayPalAdapter and StripeAdapter convert the calls to pay() in PaymentProcessor to the appropriate methods in PayPal and Stripe.
4. **Client (PaymentSystem)**: The PaymentSystem only knows about PaymentProcessor and can process payments without knowing the details of PayPal or Stripe.

---

### Summary

This exercise demonstrates the Adapter Pattern by creating adapters (PayPalAdapter and StripeAdapter) that enable a unified interface (PaymentProcessor) for handling payments with different providers. The adapters translate pay() calls into the appropriate calls for each payment provider, allowing the PaymentSystem to work seamlessly with different payment providers.

This setup makes it easy to add new payment providers by simply implementing new adapters, without modifying the main payment processing code.