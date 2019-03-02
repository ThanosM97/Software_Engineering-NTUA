import React from 'react';

export class RecordDetails extends React.Component{
    render(){
        const smartphone_details = (
            <div>
                <a>OS: </a>
                <input type='text' /> <br></br>
                <a>Οθόνη: </a>
                <input type='text' /> <br></br>
                <a>RAM: </a>
                <input type='text' /> <br></br>
                <a>Χωρητικότητα: </a>
                <input type='text' /> <br></br>
                <a>Πίσω κάμερα: </a>
                <input type='text' /> <br></br>
                <a>Μπροστά κάμερα: </a>
                <input type='text' /> <br></br>
                <a>Πυρήνες επεξεργαστή: </a>
                <input type='text' /> <br></br>
                <a>Συχνότητα επεξεργαστή: </a>
                <input type='text' /> <br></br>
            </div>
        )

        const tv_details = (
            <div>
                <a>Συχνότητα: </a>
                <input type='text' /> <br></br>
                <a>4K: </a>
                <input type='checkbox'/>  <br></br>
                <a>SmartTV: </a>
                <input type='checkbox'/>  <br></br>
            </div>
        )

        const laptop_details = (
            <div>
                <a>CPU: </a>
                <input type='text' /> <br></br>
                <a>RAM: </a>
                <input type='text' /> <br></br>
                <a>Σκληρός Δίσκος: </a>
                <input type='text' /> <br></br>
                <a>OS: </a>
                <input type='text' /> <br></br>
                <a>Μέγεθος Οθόνης: </a>
                <input type='text' /> <br></br>
                <a>Κάρτα Γραφικών: </a>
                <input type='text' /> <br></br>
            </div>
        )

        return(
            <div>
                <a>Όνομα: </a>
                <input type='text' /> *<br></br>
                <a>Τιμή: </a>
                <input type='text' /> *<br></br>
                <a>Hμερομηνία παρατήρησης: </a>
                <input type='text' /> *<br></br>
                <a>Όνομα καταστήματος: </a>
                <input type='text' /> *<br></br>
                <a>Διεύθυνση καταστήματος: </a>
                <input type='text' /> *<br></br>
                <a>Φωτογραφία: </a>
                <input type='button' value='Browse...' /><br></br>
                <a>Κατηγορία: </a>
                <select>
                    <option value='smartphone'>Smartphone</option>
                    <option value='tv'>TV</option>
                    <option value='laptop'>Laptop</option>
                </select>
                <div></div>
            </div>
        );
    }
}