import bs4
import re
import os
import getpass
from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
from time import sleep

#opening csv file and writing headers
path_to_file = "/home/" + getpass.getuser() + "/WebScrape/products/kotsovolos/"
os.makedirs(path_to_file, exist_ok = True)
filename = path_to_file + "products-monitors.csv"
f = open(filename,'w')
headers = "product_name @ itemcode @ features @ description @ price @ link\n"
f.write(headers)

for v in range (0,2):
    my_url = 'https://www.kotsovolos.gr/site/computing/monitors?v=0&flt=100&page=' + str(v)
    #opening up connection and grabbing the page
    uClient = uReq(my_url)
    page_html = uClient.read()
    uClient.close()

    #html parsing
    mypage = soup(page_html , "html.parser")

    #grabs products list
    temp = mypage.find("ul", {"class":"products list"})
    containers = temp.findAll("li" , {"class":"clearfix"})

    for container in containers:

        temp = container.find("div",{"class":"productdata"})

        name = temp.div.h4.text.strip()
        name = name.replace('/', '-')

        link = "https://www.kotsovolos.gr"+ temp.div.h4.a.get("href")

        itemcode = temp.div.p.text.strip()

        feattemp = temp.ul.findAll("li")
        features = [re.sub('[\n,\t,\r]','',b.text) for b in feattemp]

        description = re.sub('[\n,\t,\r]','',temp.find("div",{"class":"description"}).text)

        price = container.find("p",{"class":"price"}).text

        #create image
        imgpathtemp = container.find("div", {"class":"photo"})
        imgpath = "https://www.kotsovolos.gr" + imgpathtemp.a.img["src"]
        imgpath = imgpath.replace("-1m", "-1l")

        #opening img file
        path_to_img = path_to_file + "photos-monitors/"
        os.makedirs(path_to_img, exist_ok = True)
        imgfile =  open(path_to_img + name + ".jpeg" , 'wb')
        imgfile.write(uReq(imgpath).read())
        imgfile.close()

        featureslist = '    -   '.join(features)
        f.write( name + '@' +itemcode + '@' + featureslist + '@' + description + '@' + price + '@'+ link +"\n")

    #Dont forget to add the following delay. We dont want to affect
    #the site's functionality with our requests.
    sleep(5)

f.close()
