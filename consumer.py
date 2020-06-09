import requests

product1 = {"id":None, "name" : "John Wick Hoodie", "price":30.99, "description":"Iconic XL hoodie", "image":"https://kameezwale.in/wp-content/uploads/2020/01/Kameezwale-John-Wick.png", "quantity":3}
product2 = {"id":None, "name" : "Armageddon tshirt", "price":25.99, "description":"From the classic armageddon movie, an original M tshirt", "image":"https://res.cloudinary.com/teepublic/image/private/s--6uA9tHYg--/t_Resized%20Artwork/c_crop,x_10,y_10/c_fit,w_461/c_crop,g_north_west,h_626,w_470,x_0,y_0/g_north_west,u_upload:v1462829022:production:blanks:beqtwr2j6utublaobvi0,x_-395,y_-325/b_rgb:eeeeee/c_limit,f_jpg,h_630,q_90,w_630/v1446076128/production/designs/19819_0.jpg", "quantity":5}
product3 = {"id":None, "name" : "Saw tshirt", "price":19.99, "description":"L sized tshirt, form the designer Leonard Skivokk", "image":"https://cdn.shopify.com/s/files/1/2165/0371/products/man_font_23_c1f21f28-63ad-4c93-8c49-edd96a8874a0.png?v=1554898242", "quantity":2}
stock = []
stock.append(product1)
stock.append(product2)
stock.append(product3)

s = requests.Session()
r0 = s.post('http://192.168.160.67:8000/provider/login', data={'username':'dias@ua.pt', 'password':'pass'})


while True:
    print("### Welcome to your local products storage! ### \na - Check storage \nb - Load products to a website \nc - Add item to stock \nd - Exit")
    op = input("op: ")

    if (op == 'a'):
        print("### STOCK ###")
        for i in stock:
            print(i)

    elif op == 'b':
        for i in stock:
            r1 = s.post("http://192.168.160.67:8000/provider/insert", data=i)
            print(str(i) + " was successfully added!")

    elif op == 'c':
        new_product = input("New Product: ")
        stock.append(new_product)
        print(new_product + " was successful added to your stock")

    elif op == 'd':
        print("Goodbye!")
        break

    else: 
        print("Invalid input!")