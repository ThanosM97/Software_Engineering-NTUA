import React from 'react';
import ReactDOM from 'react-dom';

export class ProductList extends React.Component{
    constructor(props){
      super(props)
      this.state = {
        object: {}
      }
    }

    //returns h4 elements containing the extra data for the product
    getExtraData(product){
      var len = Object.keys(product.extraData).length
      //Array.from(Array(len).keys) creates array [0,1,2,3,...,len] and then we iterate over it
      let result = Array.from(Array(len).keys()).map(i =>
        <p key={i}> {Object.keys(product.extraData)[i] + "  :  " + Object.values(product.extraData)[i]} </p>)

      return result

    }

    render(){
        const product_list = this.props.products.map((product,i) =>
            <div key={i} className='product-entry'>
                <img src={product.img} className='product-image' />
                <div className="product-details" >
                  <a href={'productview?id=' + product.id} className='product-name'>{product.name}</a>
                  <p className="product-description">{product.description}</p>
                  <h2 className='product-specs'>{this.getExtraData(product)}</h2>
                  <h2 className='product-price'>Χαμηλότερη καταγεγραμμένη τιμή: {product.bestPrice}&euro;</h2>
                </div>
            </div>
            );
        return(
            <div className="container">
                <div className='list' >{product_list}</div>
                <Sidebar query={this.props.query} filterChange={this.props.filterChange}/>
            </div>
        );
    }
}

class Sidebar extends React.Component{
    constructor(props) {
      super(props)
      this.state={
        filters:{
          smartphone:{
            CPUcores:"",
            RAM:"",
            SreenSize:"",
            Capacity:"",
            FrontCamera:"",
            SelfieCamera:"",
            OS:"",
          },
          tv:{
            Resolution:"",
            Smart:"",
            ScreenSize:"",
          },
          laptop:{
            CPU:"",
            CPUcores:"",
            RAM:"",
            HardDrive:"",
            OS:"",
            ScreenSize:"",
            GraphicsCard:"",
          },
          tablet: {
            ScreenSize:"",
            RAM:"",
            OS:"",
            HardDrive:"",
          },
          monitor:{
            ScreenSize:"",
            Resolution:""
          }
      }
    }
  }

    handleFilterChange(event){
      var category = event.target.parentElement.parentElement.id;
      var newObject = this.state.filters;
      (newObject[category])[event.target.name] = event.target.value;

      this.props.filterChange(newObject[category],category);
    }
    render(){

        const smartphone_filters = (
            <div id="smartphone">
                <div className="filterGroups">
                    <h3>Πυρήνες CPU</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPUcores" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPUcores" value="1"/> 1 <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPUcores" value="2"/> 2 <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPUcores" value="4"/> 4 <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPUcores" value="8"/> 8 <br />
                </div>
                <div className="filterGroups">
                    <h3>Μνήμη RAM</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="2"/> 2GB<br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="4"/> 4GB <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="6"/> 6GB <br />
                </div>
                <div className="filterGroups">
                    <h3>Χωρητικότητα</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Capacity" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Capacity" value="32"/> 32GB <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Capacity" value="64"/> 64GB <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Capacity" value="128"/> 128GB <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Capacity" value="256"/> 256GB <br />
                </div>
                <div className="filterGroups">
                    <h3>Μέγεθος Οθόνης</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="4.7"/> 4.7" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="5"/> 5.0" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="5.9"/> 5.9" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="6.3"/> 6.3" <br />
                </div>
                <div className="filterGroups">
                    <h3>Ανάλυση μπροστινής κάμερας</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="FrontCamera" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="FrontCamera" value="8MP"/> 8MPixel <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="FrontCamera" value="16MP"/> 16MPixel <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="FrontCamera" value="24MP"/> 24MPixel <br />
                </div>
                <div className="filterGroups">
                    <h3>Ανάλυση κάμερας selfie</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="SelfieCamera" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="SelfieCamera" value="8MP"/> 8MPixel <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="SelfieCamera" value="13MP"/> 13MPixel <br />
                </div>
                <div className="filterGroups">
                    <h3>Λειτουργικό Σύστημα</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value="Android"/> Android <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value="IOS"/> iOS <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value="Windows"/> Windows <br />
                </div>
                <div className="filterGroups">
                    <h3>Κατασκευαστής</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="tags" value="all"/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="tags" value="apple"/> Apple <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="tags" value="hwawei"/> Huawei <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="tags" value="samsung"/> Samsung <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="tags" value="xiaomi"/> Xiaomi <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="tags" value="honor"/> Honor <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="tags" value="lenovo"/> Lenovo <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="tags" value="nokia"/> Nokia <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="tags" value="sony"/> Sony <br />
                </div>
            </div>
        )
        const tv_filters = (
            <div id="tv">
                <div className="filterGroups">
                    <h3>Ανάλυση Οθόνης</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Resolution" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Resolution" value="4K"/> 4Κ <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Resolution" value="FHD"/> FHD <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Resolution" value="HD"/> HD <br />
                </div>
                <div className="filterGroups">
                  <h3>Τεχνολογία SmartTV</h3>
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Smart" value="yes"/> Yes <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Smart" value="no"/> No <br />
                </div>
                <div className="filterGroups">
                    <h3>Μέγεθος Οθόνης</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="24"/> 24" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="28"/> 28" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="32"/> 32" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="40"/> 40" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="48"/> 48" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="55"/> 55" <br />
                </div>
            </div>
        )
        const laptop_filters = (
            <div id="laptop">
                <div className="filterGroups">
                    <h3>CPU</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPU" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPU" value="Intel"/> Intel <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPU" value="AMD"/> AMD <br />
                </div>
                <div className="filterGroups">
                    <h3>CPU cores</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPUcores" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPUcores" value="2"/> 2 <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPUcores" value="4"/> 4 <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="CPUcores" value="8"/> 8 <br />
                </div>
                <div className="filterGroups">
                    <h3>Μνήμη RAM</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="4"/> 4GB <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="8"/> 8GB <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="16"/> 16GB <br />
                </div>
                <div className="filterGroups">
                    <h3>Σκληρός Δίσκος</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="HardDrive" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="HardDrive" value="256GB"/> 256GB <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="HardDrive" value="512GB"/> 512GB <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="HardDrive" value="1TB"/> 1TB <br />
                </div>
                <div className="filterGroups">
                    <h3>OS</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value="Linux"/> Linux <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value="Windows"/> Windows <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value="Mac-OS"/> MacOS <br />
                </div>
                <div className="filterGroups">
                    <h3>Μέγεθος Οθόνης</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="11.6"/> 11,6" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="14"/> 14" <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="15.6"/> 15,6" <br />
                </div>
                <div className="filterGroups">
                    <h3>Κάρτα γραφικών</h3>
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="GraphicsCard" value=""/> All <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="GraphicsCard" value="Nvidia"/> Nvidia <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="GraphicsCard" value="AMD"/> AMD <br />
                    <input type='radio' onClick={this.handleFilterChange.bind(this)} name="GraphicsCard" value="Intel"/> Intel <br />
                </div>
            </div>
          )
          const tablet_filters = (
            <div id="tablet">
              <div className="filterGroups">
                  <h3>Μέγεθος Οθόνης</h3>
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value=""/> All <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="small"/> Small screen <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="medium"/> Medium screen <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="large"/> Large screen <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="extra-large"/> Extra large screen <br />
              </div>
              <div className="filterGroups">
                  <h3>Μνήμη RAM</h3>
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value=""/> All <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="1"/> 1GB <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="2"/> 2GB <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="4"/> 4GB <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="RAM" value="8"/> 8GB <br />
              </div>
              <div className="filterGroups">
                  <h3>Σκληρός Δίσκος</h3>
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="HardDrive" value=""/> All <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="HardDrive" value="16"/> 16GB <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="HardDrive" value="32"/> 32GB <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="HardDrive" value="64"/> 64GB <br />
              </div>
              <div className="filterGroups">
                  <h3>OS</h3>
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value=""/> All <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value="Android"/> Linux <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value="Apple-iOS"/> Apple iOS <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="OS" value="Windows"/> Windows <br />
              </div>
            </div>
          )

          const monitor_filters = (
            <div id="monitor">
              <div className="filterGroups">
                  <h3>Μέγεθος Οθόνης</h3>
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value=""/> All <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="22"/> 22" <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="24"/> 24" <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="27"/> 27" <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="32"/> 32" <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="ScreenSize" value="34"/> 34" <br />
              </div>
              <div className="filterGroups">
                  <h3>Ανάλυση Οθόνης</h3>
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Resolution" value=""/> All <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Resolution" value="FHD"/> Full HD <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Resolution" value="QHD"/> QHD <br />
                  <input type='radio' onClick={this.handleFilterChange.bind(this)} name="Resolution" value="4K"/> 4K <br />
              </div>
            </div>
          )


            const general_filters = (
              <div>
                <form action="">
                <div className="filterGroups">
                    <h3>Κατηγορία</h3>
                    <input type='radio' name="category" value="tv"/> TV <br />
                    <input type='radio' name="category" value="smartphone"/> Smartphone <br />
                    <input type='radio' name="category" value="laptop" /> Laptop <br />
                    <input type='radio' name="category" value="tablet" /> Tablet <br />
                    <input type='radio' name="category" value="monitor" /> Monitor <br />
                    <input type="submit" className="filterButton"/>
                </div>
                </form>
              </div>
            )


        let filters;

        switch(this.props.query.category){
            case 'smartphone':
                filters = smartphone_filters;
                break;
            case 'tv':
                filters = tv_filters;
                break;
            case 'laptop':
                filters = laptop_filters;
                break;
            case 'monitor':
                filters = monitor_filters;
                break;
            case 'tablet':
                filters = tablet_filters;
                break;
            default:
                filters=general_filters;
        }
        return(
            <div className='filters' style={{marginBottom:"10%"}}>
                <h2 style={{marginLeft:"40%"}}>Φίλτρα</h2>
                <a href="/productlist" style={{marginLeft:"40%", marginTop:"5%"}}> Καθαρισμός φίλτρων </a>
                {filters}
            </div>
        );
    }
}
