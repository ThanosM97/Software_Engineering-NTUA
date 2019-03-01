import React from 'react';
import ReactDOM from 'react-dom';

const smartphone = "../static/images/smartphone250x250.png";

export class ProductList extends React.Component{
    render(){
        const product_list = this.props.products.map(product => 
            <div class='product-entry'>
                <img src={smartphone} class='product-image' />
                <a href='productview' class='product-name'>Nokia L330 under triangle radar</a><br></br>
                <a class='product-price'>Χαμηλότερη καταγεγραμμένη τιμή: {product.price}</a>
                <a class='product-specs'>Χαρακτηριστικά: Οθόνη:{product.screen} RAM:{product.ram} ROM:{product.rom}</a>
            </div>
            );
        // Maybe use the same Component for displaying specs here? We'll see
        return(
            <div>
                <h3>Αποτελέσματα Αναζήτησης:</h3>
                <div class='list'>{product_list}</div>
                <Sidebar />
            </div>
        );
    }
}

class Sidebar extends React.Component{
    render(){
        const smartphone_filters = (
            <div >
                <div>
                    <at>Πυρήνες CPU</at><br></br>
                    <input type='checkbox'/> 1 <br></br>
                    <input type='checkbox'/> 2 <br></br>
                    <input type='checkbox'/> 4+ <br></br>
                </div>
                <div>
                    <at>Συχνότητα CPU</at><br></br>
                    <input type='checkbox'/> Some hz :) <br></br>
                </div>
                <div>
                    <at>Μνήμη RAM</at><br></br>
                    <input type='checkbox'/> 1GB<br></br>
                    <input type='checkbox'/> 2GB <br></br>
                    <input type='checkbox'/> 4GB <br></br>
                    <input type='checkbox'/> >4GB <br></br>
                </div>
                <div>
                    <at>Χωρητικότητα</at><br></br>
                    <input type='checkbox'/> 16GB <br></br>
                    <input type='checkbox'/> 32GB <br></br>
                    <input type='checkbox'/> 64GB <br></br>
                    <input type='checkbox'/> >64GB <br></br>
                </div>
                <div>
                    <at>Μέγεθος Οθόνης</at><br></br>
                    <input type='checkbox'/> Έως 4.7" <br></br>
                    <input type='checkbox'/> 5.0" - 5.4"<br></br>
                    <input type='checkbox'/> 5.5" - 5.8" <br></br>
                    <input type='checkbox'/> 5.9" - 6.2" <br></br>
                    <input type='checkbox'/> 6.3" και άνω <br></br>
                </div>
                <div>
                    <at>Ανάλυση πίσω κάμερας</at><br></br>
                    <input type='checkbox'/> >8MPixel <br></br>
                    <input type='checkbox'/> >12MPixel <br></br>
                    <input type='checkbox'/> >16MPixel <br></br>
                </div>
                <div>
                    <at>Ανάλυση μπροστινής κάμερας</at><br></br>
                    <input type='checkbox'/> >8MPixel <br></br>
                    <input type='checkbox'/> >12MPixel <br></br>
                    <input type='checkbox'/> >16MPixel <br></br>
                </div>
                <div>
                    <at>Λειτουργικό Σύστημα</at><br></br>
                    <input type='checkbox'/> Android <br></br>
                    <input type='checkbox'/> iOS <br></br>
                </div>
                <div>
                    <at>Κατασκευαστής</at><br></br>
                    <input type='checkbox'/> Apple <br></br>
                    <input type='checkbox'/> Huawei <br></br>
                    <input type='checkbox'/> Samsung <br></br>
                    <input type='checkbox'/> Xiaomi <br></br>
                </div>
                <input type="button" class='button' value='Πάμε'/> 
            </div>
        )
        const tv_filters = (
            <div>
                <div>
                    <at>Δυνατότητες και Λειτουργίες</at><br></br>
                    <input type='checkbox'/> 4Κ <br></br>
                    <input type='checkbox'/> SmartTV <br></br>                    
                </div>
                <div>
                    <at>Συχνότητα</at><br></br>
                    <input type='checkbox'/> έως 50/60Hz <br></br>
                    <input type='checkbox'/> έως 120Hz <br></br>
                    <input type='checkbox'/> >120Hz <br></br>
                </div>
            </div>
        )
        const laptop_filters = (
            <div>
                <div>
                    <at>CPU</at><br></br>
                    <input type='checkbox'/> Intel core i3 <br></br>
                    <input type='checkbox'/> Intel core i5 <br></br>
                    <input type='checkbox'/> Intel core i7<br></br>
                    <input type='checkbox'/> AMD A-Series <br></br>
                    <input type='checkbox'/> AMD E-Series <br></br>
                </div>
                <div>
                    <at>Μνήμη RAM</at><br></br>
                    <input type='checkbox'/> 2GB <br></br>
                    <input type='checkbox'/> 4GB <br></br>
                    <input type='checkbox'/> 8GB <br></br>
                    <input type='checkbox'/> 16GB <br></br>
                    <input type='checkbox'/> >16GB <br></br>
                </div>
                <div>
                    <at>Σκληρός Δίσκος</at><br></br>
                    <input type='checkbox'/> HDD <br></br>
                    <input type='checkbox'/> SSD <br></br>
                </div>
                <div>
                    <at>OS</at><br></br>
                    <input type='checkbox'/> Linux <br></br>
                    <input type='checkbox'/> MacOS <br></br>
                    <input type='checkbox'/> Windows 7 <br></br>
                    <input type='checkbox'/> Windows 10 <br></br>
                </div>
                <div>
                    <at>Μέγεθος Οθόνης</at><br></br>
                    <input type='checkbox'/> 11,6" <br></br>
                    <input type='checkbox'/> 12,5" <br></br>
                    <input type='checkbox'/> 13,3" <br></br>
                    <input type='checkbox'/> 14" <br></br>
                    <input type='checkbox'/> 15,6" <br></br>
                    <input type='checkbox'/> >17" <br></br>
                </div>
                <div>
                    <at>Κάρτα γραφικών</at><br></br>
                    <input type='checkbox'/> Nvidia <br></br>
                    <input type='checkbox'/> AMD <br></br>
                    <input type='checkbox'/> Intel <br></br>
                </div>
            </div>
        )
        return(
            <div class='filters'>
                <div class='filter-name'>Φίλτρα</div>
                {tv_filters}
            </div>
        );
    }
}