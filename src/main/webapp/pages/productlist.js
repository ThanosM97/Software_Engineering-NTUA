import {ProductList} from '../components/ProductList.js';
import Head from 'next/head';
import NavBar from '../components/NavBar';
import Footer from '../components/Footer';

// I'm going to assume that We get the data at some point to this format
const products = [
    {device: 'smartphone', name: 'Testphone', screen: '5"', ram: '4GB', rom: '64GB', backCamera: '20Mp',
    frontCamera: '4Mp', cpuCores: '4', manufacturer: 'Samsung', price: '300'},
    {device: 'smartphone', name: 'Exphone', screen: '4"', ram: '2GB', rom: '64GB', backCamera: '20Mp',
    frontCamera: '4Mp', cpuCores: '2', manufacturer: 'Fiphone', price: '300'},
    {device: 'smartphone', name: 'RADPHONE', screen: '18"', ram: '9TB', rom: '129GB', backCamera: '20Mp',
    frontCamera: '100Mp', cpuCores: '1000', manufacturer: 'RADPHONES COMPANY', price: '400'}
]

var content = (
    <div>
        <Head>
          <title> Λίστα προϊόντων | Stingy </title>
          <link rel="shortcut icon" href="../static/logo/logo.png"/>
          <link href='../static/ProductList.css' type='text/css' rel='stylesheet' />
        </Head>
        <div class='background'>
                <NavBar />
                <ProductList products={products} />
                <Footer />
        </div>
    </div>
);
export default () => content;
