# Reliable Tuya

Tuya / Smart Life make good value smart home IoT products but they are fundamentally unreliable in my home. The aim behind this project is a simple application that enhances their reliability by maintaining
a persistent store representing the global source of truth for the state of devices linked to a given Tuya / Smart Life account.

# Getting Started

## Prerequisites

- Java (>17) installed

## Configuration Values

You will need to following things to make this project work - 

 ```
 Access ID / Client ID
 Access Secret / Client Secret
 Home Id
 ```

Access id and access secret are available from the Tuya IoT project overview page - log in to the Tuya IoT platform [here](https://iot.tuya.com). Once logged in, from the left hand menu
select 'Cloud', this will take you to your projects (you will need to create one if you don't already have one).
Next click, 'Open Project' on the right hand side for the project you wish to integrate against, this will take you to 
the project overview page, which displays the access id and access secret. 

Edit the application.properties to add:

```
connector.ak=ACCESS_ID
connector.sk=ACCESS_KEY
```

Next you will need your home id. This can be found by using the API debug functionality to request it via your user id or uid (I couldn't find
an easier way to determine this using the Tuya ui).

From the overview page, click the 'Devices' tab, from this page there are a sub-section of tabs, from these tabs click 'Link App Account'. This 
will display your user, and your uid. 

_TODO_ - Add instructions about how to add an app account.

Now click the 'All Devices' tab, and on any device, click 'Debug Device'.

Using the `Query Home List` API from the `Home Management` section of the API you can use your uid to lookup your home id.

Add the home id in the application.properties:

```
tuya.home-id=HOME_ID
```

**Note**
You may also need to update your `connector.region` value in the application.properties to reflect the region your Tuya account is hosted in.

# Running the Application

*Note: You will need to set `--add-opens java.base/java.lang=ALL-UNNAMED` in the VM options of your IDE run configuration as the Tuya connector won't work without this.

Currently the app is only configured to run from the IDE (working on a Docker compose for this). 

To start the app, run the main method in `ReliableTuyaApplicationTests` in the test package (this will start the production app 
using a MySQL testcontainer to avoid the need for an embedded H2 database).