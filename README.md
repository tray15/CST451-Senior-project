# Senior Project - PC Part Web Shop

<h2>Overview</h2>
This project was created because I have a passion for building computer parts and I thought a web shop would be a great way to combine my enjoyment of programming with buying parts for building computers, and also being able to share that with others. The project was created utilizing the MVC design pattern and utilizes JavaEE (SE8), and runs on a Wildfly 11 server. This project also utilized an N-layer architecture to spread the operations out of each layer. If this were a larger site with a lot of traffic, the load would be able to balanced among the different tiers. They also help abstract away the code to create a more clean and readable environment.
<h2>User guide</h2>
1) Register:<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/userguide/1st_step.png?raw=true"></img>
Enter in a username, password, and then click submit. The username and password must be between 3 and 50 characters in length and cannot be empty.<br />
2) Login:<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/userguide/2nd_step.png?raw=true"></img>
Enter in the credentials you just created in the registration process. This will log you in and allow you to use the platform. Upon logging in, you will be taken to the welcome page.<br />
3) Navigate to products:<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/userguide/3rd_step.png?raw=true"></img>
Select the products link in the navigation bar at the top.<br />
4) Browse products:<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/userguide/4th_step.png?raw=true"></img>
Browse the products.<br />
5) Add product to cart<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/userguide/5th_step.png?raw=true"></img>
Add any product you'd like to purchase into your cart.<br />
6) Finalize purchase and check out<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/userguide/6th_step.png?raw=true"></img>
Finalize your purchase and ensure that products that you wish to purchase are in your cart and the correct products are there. Remove any products you do not wish to purchase, then check out.
7) View orders page<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/userguide/7th_step.png?raw=true"></img>
After completing the purchase, you will be taken to your orders page where you can see what products you have purchased. This is also able to be viewed at a later time by using the navigation bar at the top.
8) (Optional): Logout when finished with your browsing session by clicking "Logout" in the navigation bar.
<h2>Admin guide</h2>
1) Login with Admin account<br />
2) Click "Add Product" on Navigation bar to add a new product to the inventory. A new form will show where you can enter the information for the product.<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/adminguide/add_product.png?raw=true"></img>
3) Click on the "Products" link in the navigation bar to update or remove a product from inventory<br />
4) For updating a product, click the "Update" button next to the product you'd like to update.<br /><img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/adminguide/update_1st.png?raw=true"></img>A new form will show with the information on your product. Enter any changes in this form and submit the changes.<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/adminguide/update_2nd.png?raw=true"></img>
5) For removing a product, click the "Delete from Inventory" button next to the product you'd like to remove.<br />
<h2>Design Diagrams</h2>
MVC Design Diagram<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/design_diagrams/mvc_design.png?raw=true"></img><br />

Database ER diagram<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/design_diagrams/databasediagram.png?raw=true"></img><br />

Sequence diagrams<br />
Register/Login<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/design_diagrams/register_sequence.png?raw=true"></img><br />

Add to Cart/Check out<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/design_diagrams/cart_checkout_diagram.png?raw=true"></img><br />

UI Navigation Diagram<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/design_diagrams/ui_nav_diagram.png?raw=true"></img><br />

Data Models<br />
<img src="https://github.com/tray15/CST451-Senior-project/blob/main/documentation/design_diagrams/data_model_diagrams.png?raw=true"></img><br />
