function ErrorMessage({ message, title = "Error" }) {
    return (
        <div className='text-center mt-5'>
            <h3>{title}</h3>
            <p className='text-muted'>{message}</p>
        </div>
    );
}

export default ErrorMessage;