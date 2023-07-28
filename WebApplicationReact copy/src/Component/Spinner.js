import Container from 'react-bootstrap/esm/Container';
import image from '../img/giphy_s.gif'
import '../index.css';

const LoadingSpinner = () => {
    return (
        <Container>
            <div className="flex" id="container">
                <img className='' src={image}></img>
            </div>
        </Container>
    )
}

export default LoadingSpinner