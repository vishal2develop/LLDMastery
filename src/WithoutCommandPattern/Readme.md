## Without Command Pattern
Simple Example: Remote Control for Home Automation
Let's consider a remote control that can control different devices (like lights, fans, or TVs). The Command Pattern allows us to encapsulate each button press on the remote control as a command. This makes it easy to configure different actions (like turning on/off devices) without tightly coupling the remote control to the specific device classes.

**Issues:**
- Tightly coupled: The remote control is directly dependent on the devices (Light, Fan). Adding new devices or operations would require changing the RemoteControl class, violating the Open/Closed Principle.
- Hard to extend: Adding new behaviors (e.g., turning off the light) would require modifying the same class.

