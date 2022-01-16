# logistics-inventory-manager
Coding assignment for Shopify Backend Developer Intern  Challenge - Summer 2022

## Live Application [Swagger UI]
A live version of the deployed application can be found on - https://sheltered-woodland-98473.herokuapp.com/swagger-ui.html#/

## API Details
The project has two controller classes :
* Item Controller - Enables CRUD operations on inventory item.
* CSV Export - Enables CSV download of all items.

## API Usage and Description
### How to use Swagger
#### Item Controller
* [GET] /item - **Click on the API -> Try it out -> Execute -> See list of all items**
* [POST] /item -  **Click on the API -> Try it out -> Add appropriate data in JSON format -> Execute**
* [POST] /item/delete - **Click on the API -> Try it out -> Enter name of item you wish to delete as JSON -> Execute**
* [PUT] /item/edit - **Click on the API -> Try it out -> Enter name of application you wish to edit and updated values -> Execute**

#### CSV Export Controller
* [GET] /export - **Click on the API -> Try it out -> Execute -> Download -> Get CSV file**

### Defition and Usage
#### Item Controller
* [GET] /item - **It is used to get a list of all items in the inventory.**
* [POST] /item -  **It is used to create a new item. Item name is unique as it is used for other operations. An auto-generated ID gets added before saving to the DB.**
* [POST] /item/delete - **Items can be deleted using the name of the item. As of now we are not doing any sanity check and if the item name does not exist it does nothing.**
* [PUT] /item/edit - **Item description, type and price can be updated by providing updated values and item name. Again, we have not put into place any sanity checks.**

#### CSV Export Controller
* [GET] /export - **It is used to create a downloadable CSV file of the data.**

## Scalability and Extension
The following could be possibile extensions and scalability opportunities :
* Item being an entity allows for multiple types. Types can be a preconfigured table with available options for user to choose from. 
* Inventory with item list and quantity of each item. 
* Replicate CSV Export functionality to add other file formats.
* Extend service classes for inventory and type based on need.
* Adding sanity checks on CRUD operations. 
* Adding APIs to get items by name.

## Known Limitations
* Get all items returns a list of "entity" which is not desirable. Model mapper can be utilised to convert this into list of ItemDTO.
* Error handling needs to be updated inorder to catch and return readable error messages.
* Logger needs to be added inorder to capture appropriate logs. 