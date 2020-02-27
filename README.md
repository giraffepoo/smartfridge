### Server Address
http://smartfridgeeightb.azurewebsites.net/

## API for FoodItem

### Structure:
 - String name of food item
 - Int quantity of food item (increments on each add)
 - Date timestamp of creation date
 - (TODO) Expiry date?
 
 Example:
 
    [{
        "name": "banana",
        "quantity": 2,
        "createdDate": "2020-02-08T01:33:32.628+0000"
    },
    {
        "name": "tuna",
        "quantity": 1,
        "createdDate": "2020-02-08T01:34:56.570+0000"
    },
    {
        "name": "apple",
        "quantity": 1,
        "createdDate": "2020-02-08T01:37:51.515+0000"
    }]

|Action  |Endpoint  | Payload |
|--|--|--|
| POST addFoodItem | /api/fooditem/add | (format: text)<br />  -name of item |
| POST addFoodItem | /api/fooditem/camera/add | (format: JSON)<br /> `{"labels": ["a","b"], "similarity": 0.0}` |
| POST removeFoodItem | /api/fooditem/remove | (format: text)<br /> -name of item |
|GET getAllFoodItems|/api/fooditem|
|GET getFoodItem|/api/fooditem/{name}|
|DELETE deleteFoodItem|/api/fooditem/{name}|
|GET getAllFoodItems|/api/fooditem|
|GET getAllFoodItemsText|/api/fooditem/text/all-items||
|GET getAllLowQuantityItemsText|/api/fooditem/text/low-quantity-items||
|GET textSuggestedRecipes|/api/recipes/text/suggest-recipes||