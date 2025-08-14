import heroImage from '../../../assets/hero-image.png';

function HeroImage() {

    return (
        <>
            <div className='hero-image'>
                <img src={heroImage} alt="The League Standard" className='hero-img'/>
            </div>
        </>
    )
}

export default HeroImage;