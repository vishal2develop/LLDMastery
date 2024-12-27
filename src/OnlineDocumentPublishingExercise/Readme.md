## Exercise: Template Method Pattern for Online Document Publishing
### Problem Statement
Imagine you are developing a Document Publishing System that allows users to create different types of documents such as Blogs, News Articles, and Research Papers. Each type of document follows a similar publishing process, but some steps are unique to each document type.

Steps in the publishing process:

- **Write Content:** Create the main body of the document.
- **Edit Content:** Review and edit the document.
- **Publish Content:** Make the document available online.
- **Add Specific Elements** (custom for each document):
  - **Blog**: Add images and tags.
  - **News Article**: Add a headline and summary.
  - **Research Paper**: Add references and citations.

Your task is to implement this system using the `Template Method Pattern` to ensure a consistent publishing process while allowing customization for different document types.

---

## Steps to Implement the Template Method Pattern

### Step 1: Define the Abstract Class (Template)
Create an abstract class `DocumentTemplate` that defines the template method and common steps.

### Step 2: Create Concrete Classes
Implement specific document types by extending the DocumentTemplate class and providing custom implementations

### Step 3: Create the Client Code
The client code will use the template method to publish different types of documents.

---

### Real-World Use Cases of Template Method Pattern

**1. Report Generation:**
- Different types of reports (e.g., PDF, Excel, HTML) can follow a standard generation process but with specific formatting requirements.

**2. Game Development:**
- Different levels or missions in a game might share a common structure but differ in their specific challenges or objectives.

**3. Data Processing Pipelines:**
- Common steps like data validation, transformation, and storage can be defined in a template, with specific data handling implemented in subclasses.