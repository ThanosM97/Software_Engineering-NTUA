import React from 'react';
import ReactDOM from 'react-dom';

const myExampleProduct = {
    device: 'smartphone', name: 'Testphone', screen: '5"', ram: '4GB', rom: '64GB', backCamera: '20Mp', 
    frontCamera: '4Mp', cpuCores: '4', manufacturer: 'Samsung'
};

const myExampleShops = [
    {name: 'Λαική αγορά Αιγαλέου', price: '5$', otherData: ''},
    {name: 'Public Συντάγματος', price: '1000$', otherData: ''}
];

const smartphone = "../static/images/smartphone250x250.png";
const like = "../static/images/like.png";
const unlike = "../static/images/unlike.png";
const questionmark = "../static/images/questionmark.png";


export class ProductView extends React.Component{
    render(){
        // We should give as the product argument the 'category' argument from the URL
        // For testing I am going to assume the URL is of type www.stingy.com/category='category'&
        return(
            <div>
                <ProductViewSpecs class='main-product' product={this.props.product}/>  
                <ProductViewShopList class='main-shops' shops={this.props.shops}/>
            </div>
        );
    }
}

export class ProductViewSpecs extends React.Component{
    render(){
        let product_render;
        switch (this.props.product.device){
            case 'smartphone':
                product_render = ( <ProductViewSmartphone product={this.props.product} />);
                break;
            case 'tv':
                product_render = ( <ProductViewTv product={this.props.product} />);
                break;
            case 'monitor':
                product_render = ( <ProductViewMonitor product={this.props.product} />);
                break;
            case 'tablet':
                product_render = ( <ProductViewTablet product={this.props.product} />);
                break;
            case 'laptop':
                product_render = ( <ProductViewLaptop product={this.props.product} />);
                break;
            default:
                product_render = ( <div> Oops! Invalid product type :( </div> );
            }

        // if (this.props.product.device == 'smartphone'){
        //     product_render = ( <ProductViewSmartphone product={this.props.product} />);
        // }
        // else if
        //     product_render = ( <div> Oops! </div> );
        // }
        return(
                <div class='specs'>
                    <div>{product_render}</div>
                </div>
        );
    }
}

class ProductViewSmartphone extends React.Component{
    render(){
        return(
            <div>
                <a class='product-name'>{this.props.product.name}</a><br />
                <img src={smartphone} class='product-image' /><br />
                <table class='specs-table'>
                    <tr>
                        <td>Οθόνη</td> 
                        <td>{this.props.product.screen}</td>
                    </tr>
                    <tr>
                        <td>Μνήμη RAM</td> 
                        <td>{this.props.product.ram}</td>
                    </tr>
                    <tr>
                        <td>Μνήμη ROM</td> 
                        <td>{this.props.product.rom}</td>
                    </tr>
                    <tr>
                        <td>Κάμερα</td> 
                        <td>{this.props.product.backCamera}</td>
                    </tr>
                    <tr>
                    <td>Selfie</td> 
                    <td>{this.props.product.frontCamera}</td>
                    </tr>
                    <tr>
                        <td>Πυρήνες Επεξεργαστή</td> 
                        <td>{this.props.product.cpuCores}</td>
                    </tr>
                    <tr>
                        <td>Κατασκευαστής</td> 
                        <td>{this.props.product.manufacturer}</td>
                    </tr>
                </table>
            </div>
        );
    }
}

class ProductViewTv extends React.Component{
    render(){
        return(
            <div>
                <a>Όνομα: {this.props.name}</a>
                <a>Διαγώνιος: {this.props.inches}</a>
                <a>Ευκρίνεια: {this.props.resolution}</a>
                <a>Τύπος Panel: {this.props.panel}</a>
                <a>Smart: {this.props.isSmart}</a>
                <a>Κατασκευαστής: {this.props.manufacturer}</a>
            </div>
        );
    }
}

class ProductViewMonitor extends React.Component{
	render(){
		<h1>Coming Soon</h1>
	}
}


class ProductViewTablet extends React.Component{
	render(){
		<h1>Coming Soon</h1>
	}
}

class ProductViewLaptop extends React.Component{
	render(){
		<h1>Coming Soon</h1>
	}
}

// class ProductViewShop extends React.Component{
//     render(){
//         return(
//             <div>
//                 <a>Όνομα: {this.props.shop.name}</a><br />
//                 <a>Τιμή: {this.props.shop.price}</a><br />
//                 <a>Άλλα στοιχεία: {this.props.shop.otherData}</a><br />
//             </div>
//         );
//     }
// }

class ProductViewShopList extends React.Component{
    render(){
        const shoplist = this.props.shops.map(shop => 
            <div class='shopbox'>
                <as class='shopname'>{shop.name} </as>
                <as class='price'>{shop.price} </as><br></br> 
                <as>Άλλα στοιχεία: {shop.otherData} </as><br></br>
                <as>Αξιοπιστία καταχώρησης: <img src={like} class='like-img'/> <img src={unlike} class='like-img'/><img src={questionmark} class='questionmark-img'/></as><br></br>
                <as class='address'>Αλέκου Παναγούλη 9, Νίκαια </as><br></br>
            </div>
            );
        return(
            <div>
                <h3>Διαθέσιμο στα εξής Καταστήματα:</h3>
                <div class='myshoplist'>{shoplist}</div>
            </div>
        )
    }
}
