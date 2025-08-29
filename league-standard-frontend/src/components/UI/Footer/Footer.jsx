

function Footer() {
    const year = new Date().getFullYear();

    return(
        <>
            <div className="container text-center text-muted mt-5 mb-3">
                <p>&copy; {year} 🏀 The League Standard.</p>
            </div>
        </>
    );
}

export default Footer;