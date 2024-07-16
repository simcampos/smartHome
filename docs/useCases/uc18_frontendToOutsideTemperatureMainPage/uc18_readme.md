# UC18

## 0. Description

To show the outside temperature in the main page.

## 1. Analysis
The system must provide the user with the ability to see the outside temperature in the main page. The system must show the outside temperature in the main page.

### 1.1. Use Case Description
_To show the outside temperature in the main page_

    Use Case Name: To show the outside temperature in the main page.

    Actor: User

    Goal: To show the outside temperature in the main page

    Preconditions:
    The user must be connected to VPN DEI.    

    Basic Flow:
    1. The user opens the main page.
    
    Alternative Flows:
    1. The user is not connected to VPN DEI.


### 1.2. Dependency on other use cases
None.

### 1.3. System Sequence Diagram
![UC018-SSD](artifacts/uc18_SSD_v1.svg)

## 2. Design

### 2.1. Sequence Diagram
![UC018-SD](artifacts/uc18_SD_v2.svg)


### 2.3 Applied Patterns
- All classes have only one and well-defined responsibility.
- **Container Components:** We use container components to manage state and business logic.
- **Presentational Components:** Presentational components focus solely on rendering UI based on the props they receive. 
- **Material-UI for UI Components**: Instead of CSS Modules, we integrate Material-UI for styling and UI components.
- **State Management with Redux**: We employ Redux to manage the global state of our application.