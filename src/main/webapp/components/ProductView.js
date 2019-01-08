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

class ProductView extends React.Component{
    render(){
        return(
            <div>
                <ProductViewSpecs product={myExampleProduct}/>  
                <ProductViewShops shops={myExampleShops}/>
            </div>
        );
    }
}

class ProductViewSpecs extends React.Component{
    render(){
        switch (this.props.device){
            case 'smartphone':
                return (
                    <ProductViewSmartphone product={this.props.product} />
                );
            case 'tv':
                return (
                    <ProductViewTv />
                );
            case 'monitor':
                return (
                    <ProductViewMonitor />
                );
            case 'tablet':
                return (
                    <ProductViewTablet />
                );
            case 'laptop':
                return (
                    <ProductViewLaptop />
                );
            default: 
                return (
                    <h1>Not a valid product type!</h1>
                );
        }
    }
}

class ProductViewSmartphone extends React.Component{
    render(){
        return(
            <div>
                <a>Όνομα: {this.props.name}</a>
                <a>Οθόνη: {this.props.screen}</a>
                <a>Μνήμη RAM: {this.props.ram}</a>
                <a>Μνήμη ROM: {this.props.rom}</a>
                <a>Κάμερα: {this.props.backCamera}</a>
                <a>Selfie: {this.props.frontCamera}</a>
                <a>Πυρήνες Επεξεργαστή: {this.props.cpuCores}</a>
                <a>Κατασκευαστής: {this.props.manufacturer}</a>
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
