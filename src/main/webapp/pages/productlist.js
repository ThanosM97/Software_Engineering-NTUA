import {ProductList} from '../components/ProductList.js';

// I'm going to assume that We get the data at some point to this format
const products = [
    {device: 'smartphone', name: 'Testphone', screen: '5"', ram: '4GB', rom: '64GB', backCamera: '20Mp', 
    frontCamera: '4Mp', cpuCores: '4', manufacturer: 'Samsung'},
    {device: 'smartphone', name: 'Exphone', screen: '4"', ram: '2GB', rom: '64GB', backCamera: '20Mp', 
    frontCamera: '4Mp', cpuCores: '2', manufacturer: 'Fiphone'},
    {device: 'smartphone', name: 'RADPHONE', screen: '18"', ram: '9TB', rom: '129GB', backCamera: '20Mp', 
    frontCamera: '100Mp', cpuCores: '1000', manufacturer: 'RADPHONES COMPANY'}
]

var content = (
    <div>
            <h1>Coming Soon :)</h1>
            <a>Related Products:</a>
            <ProductList products={products} />
    </div>
);
export default () => content;