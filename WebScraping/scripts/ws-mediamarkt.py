import bs4
import re
import os
import getpass
from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
from time import sleep

#opening csv file and writing headers
path_to_file = "/home/" + getpass.getuser() + "/WebScrape/products/MediaMarkt/"
os.makedirs(path_to_file, exist_ok = True)
filename = path_to_file + "products-TVs.csv"
f = open(filename,'w')
headers = "product_name @ features @ price @ link\n"
f.write(headers)

for v in range (1,10):
    my_url = 'https://www.mediamarkt.gr/el/category/_%CF%84%CE%B7%CE%BB%CE%B5%CE%BF%CF%81%CE%AC%CF%83%CE%B5%CE%B9%CF%82-633616.html?searchParams=&sort=&view=PRODUCTLIST&page='+ str(v)

    #opening up connection and grabbing the page
    uClient = uReq(my_url)
    page_html = uClient.read()
    uClient.close()

    #html parsing
    mypage = soup(page_html , "html.parser")

    #grabs products list
    containers = mypage.findAll("div",{"class":"product-wrapper"})

    for container in containers:

        temp = container.find("div",{"class":"content"})

        name = re.sub('[\n,\t,\r,\xa0]',"",temp.h2.text.strip())

        link = "https://www.mediamarkt.gr" + temp.h2.a.get("href")

        temp = container.find("div",{"class":"box infobox"})
        price = temp.div.div.text.strip().replace("-","")

        temp = container.find("dl", {"class":"product-details"})
        feats1 = temp.findAll("dt")
        feats2 = temp.findAll("dd")
        features = [feats1[i].text + " " + feats2[i].text for i in range(len(feats1))]
        features = str.join(" - ", features)

        features = re.sub('[\n,\t,\r,\xa0]',"",features.strip())

        imgpath = ("https:" + container.find("img").get("data-original")).replace("240_148", "786_587")

        #opening img file
        path_to_img = path_to_file + "photos-TVs/"
        os.makedirs(path_to_img, exist_ok = True)
        imgfile =  open(path_to_img + name.replace('/','-') + ".png" , 'wb')
        try:
            imgfile.write(uReq(imgpath).read())
        except:
            imgfile.write(uReq("https://www.e-shop.gr/images/nothere.gif").read())
        imgfile.close()

        f.write( name + " @ " + features + " @ " + price + " @ " + link + "\n")

    #Dont forget to add the following delay. We dont want to affect
    #the site's functionality with our requests.
    sleep(5)

f.close()
