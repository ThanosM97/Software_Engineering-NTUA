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

export class ProductView extends React.Component{
    render(){
        return(
            <div>
                <ProductViewSpecs product={myExampleProduct}/>  
                <ProductViewShops shops={myExampleShops}/>
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
                <div>{product_render}</div>
        );
    }
}

export class ProductViewSmartphone extends React.Component{
    render(){
        return(
            <div>
                <a>Όνομα: {this.props.product.name}</a><br />
                <a>Οθόνη: {this.props.product.screen}</a><br />
                <a>Μνήμη RAM: {this.props.product.ram}</a><br />
                <a>Μνήμη ROM: {this.props.product.rom}</a><br />
                <a>Κάμερα: {this.props.product.backCamera}</a><br />
                <a>Selfie: {this.props.product.frontCamera}</a><br />
                <a>Πυρήνες Επεξεργαστή: {this.props.product.cpuCores}</a><br />
                <a>Κατασκευαστής: {this.props.product.manufacturer}</a><br />
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

class ProductViewShops extends React.Component{
    render(){
        // const listItems = strings.map( ...
        return(
		<div>
        	    <h1>Μαγαζιά που πωλούν το προϊόν:</h1>
		    <h2>Coming soon :)</h2>
		</div>
        );
    }
}
