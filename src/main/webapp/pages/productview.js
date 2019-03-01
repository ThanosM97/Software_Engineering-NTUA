import {ProductView} from '../components/ProductView.js';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';


const myExampleProduct = {
    device: 'smartphone', name: 'Testphone 5inch 4GB RAM Black', size: '6"', ram: '4GB', capacity: '32GB', backCamera: '20Mp', 
    frontCamera: '4Mp', cpuCores: '4', cpuFrequency: '1.8 GHz', OS: 'Android'
};

const myExampleShops = [
    {name: 'Λαική αγορά Αιγαλέου', price: '5$', otherData: ''},
    {name: 'Public Συντάγματος', price: '1000$', otherData: ''},
    {name: 'Πλαίσιο Πειραιά', price: '1000$', otherData: ''}
];

var content = (
    <div>
        <head>
            <link href='../static/ProductView.css' type='text/css' rel='stylesheet' />
        </head>
        <div>
            <NavBar />
            <ProductView product={myExampleProduct} shops={myExampleShops} />
            <Footer />
        </div>
    </div>
);
export default () => content;
