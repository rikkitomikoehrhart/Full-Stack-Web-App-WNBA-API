function Loading() {
    return (
        <>
            <div className="mt-3 container text-center">
                <div className="spinner-border text-success" role="status">
                    <span className="visually-hidden">Loading...</span>
                </div>
                <h6 className="mt-2 loading-text">Loading...</h6>
            </div>
        </>
    );
}

export default Loading;