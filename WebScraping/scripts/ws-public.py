import bs4
import re
import os
import getpass
from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
from time import sleep

#opening csv file and writing headers
path_to_file = "/home/" + getpass.getuser() + "/WebScrape/products/public/"
os.makedirs(path_to_file, exist_ok = True)
filename = path_to_file + "products-printers.csv"
f = open(filename,'w')
headers = "product_name @ features @ price @ link\n"
f.write(headers)

for v in range (0,1,90):
    my_url = 'https://www.public.gr/cat/perifereiaka/printers/ektypotes/?Nrpp=90'
    #opening up connection and grabbing the page
    uClient = uReq(my_url)
    page_html = uClient.read()
    uClient.close()

    #html parsing
    mypage = soup(page_html , "html.parser")

    #grabs products list
    containers = mypage.findAll("div", {"class":"col-sm-6 col-lg-4"})

    for container in containers:

        temp = container.find("div",{"class":"teaser--product-details"})

        link = "https://www.public.gr" + temp.a.get("href")
        link = link.replace('”', '')
        link = link.replace('™','')

        name = re.sub('[\n,\t,\r,\xa0]',"",temp.text.strip())

        temp = container.find("div",{"class":"teaser--product-final-price large"})
        price = temp.get("data-price")


        temp = container.find("img",{"class":"teaser-techPage image"})
        imgpath = "https:" + temp.get("src")

        #opening img file
        path_to_img = path_to_file + "photos-printers/"
        os.makedirs(path_to_img, exist_ok = True)
        imgfile =  open(path_to_img + name.replace('/','-') + ".jpeg" , 'wb')
        try:
            imgfile.write(uReq(imgpath).read())
        except:
            imgfile.write(uReq("https://www.e-shop.gr/images/nothere.gif").read())
        imgfile.close()


        featclient = uReq(link)
        page2 = featclient.read()
        featclient.close()
        mypage2 = soup(page2 , "html.parser")

        feats = mypage2.findAll("div", {"class":"product-info-item"})
        features = [x.text for x in feats]
        features = str.join("-",features).strip()

        f.write( name + "@" + features + "@" + price + "@" + link + "\n")

    #Dont forget to add the following delay. We dont want to affect
    #the site's functionality with our requests.
    sleep(5)

f.close()
