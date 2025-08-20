

function Gallery({ image_urls }) {

    return (
        <>
            <div id="carouselExampleFade" className="carousel slide carousel-fade shadow-sm m-4" >
                <div className="carousel-indicators">
                    {image_urls.map((_, index) => (
                        <button 
                            key={index}
                            type="button" 
                            data-bs-target="#carouselExampleFade" 
                            data-bs-slide-to={index} 
                            className={index === 0 ? 'active' : ''}
                            aria-current={index === 0 ? 'true' : 'false'}
                            aria-label={`Slide ${index + 1}`}
                        ></button>
                    ))}
                </div>

                <div className="carousel-inner" style={{ height: '400px' }}>
                    {image_urls.map((image, index) => (
                        <div key={index} className={`carousel-item ${index === 0 ? 'active' : ''}`}> 
                            <img src={image} className="d-block w-100 rounded" style={{height: '400px', objectFit: 'cover', objectPosition: 'center'}}/>
                        </div>
                    ))}
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleFade" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </>
    );
}

export default Gallery;