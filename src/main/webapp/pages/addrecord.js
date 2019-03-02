import NavBar from '../components/NavBar';
import Footer from '../components/Footer';
import {RecordDetails} from '../components/AddRecord';

var content = (
    <div>
        <NavBar />
        <h2>Προσθήκη Προϊόντος</h2>
        <RecordDetails />
        <Footer />
    </div>
)
export default () => content;