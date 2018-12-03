import bs4
import re
import sys
import getpass
import os
from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
from time import sleep

#opening csv file and writing headers
path_to_file = "/home/" + getpass.getuser() + "/WebScrape/products/e-shop/"
os.makedirs(path_to_file, exist_ok = True)
filename = path_to_file + "products-smartwatches.csv"
f = open(filename,'w')
headers = "product_name @ itemcode @ features @ price @ link\n"
f.write(headers)

for v in range (0,41,10):
    my_url = 'https://www.e-shop.gr/tilepikoinonies-smartwatches-list?offset='+str(v)+'&table=TEL&category=SMARTWATCHES'
    #opening up connection and grabbing the page
    uClient = uReq(my_url)
    page_html = uClient.read()
    uClient.close()

    #html parsing
    mypage = soup(page_html , "html.parser")

    #grabs products list
    containers = mypage.findAll("table", {"class":"web-product-container"})
    links = mypage.findAll("td", {"class":"web-product-photo"})
    i = 0

    for container in containers:

        link = links[i].a.get("href")
        i+=1

        temp = container.find("td",{"class":"web-product-title"})

        name = temp.a.text.strip()
        name = name.replace('/', '-')

        itemcode = temp.font.text.strip()
        itemcode = re.sub('[(,)]' , '', itemcode)

        feattemp = container.findAll("td", {"class":"web-product-info"})
        features = re.sub('[\n,\t,\r,\xa0]','',feattemp[1].text)

        price = container.find("td",{"class":"web-product-price"}).text
        price = re.sub('[\n,\t,\r,\xa0]','',price)

        #in case you crawl for items in a category other than telecommunications
        #you have to change TEL to PER at the path below
        imgpath = "https://www.e-shop.gr/images/TEL/BIG/" + re.sub('[(,)]', '', itemcode) + '.jpg'

        #opening img file
        path_to_img = path_to_file + "photos-smartwatches/"
        os.makedirs(path_to_img, exist_ok = True)
        imgfile =  open(path_to_img + name + ".jpeg" , 'wb')
        try:
            imgfile.write(uReq(imgpath).read())
        except:
            imgfile.write(uReq("https://www.e-shop.gr/images/nothere.gif").read())

        imgfile.close()

        f.write( name + '@' +itemcode + '@' + features + '@' + price + '@' + link + "\n")

    #Dont forget to add the following delay. We dont want to affect
    #the site's functionality with our requests.
    sleep(5)

f.close()
