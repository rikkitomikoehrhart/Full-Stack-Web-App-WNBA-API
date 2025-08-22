export function getFormattedDate(dateString) {
    const date = new Date(dateString);
    const month = date.toLocaleDateString('default', { month: "short" });
    return `${month} ${date.getDate()}, ${date.getFullYear}`;
}

export function getFormattedDateTime(dateString) {
    const date = new Date(dateString);
    const month = date.toLocaleDateString('default', { month: "short" });
    const time = date.toLocaleString('en-US', {
        hour: 'numeric',
        minute: 'numeric',
        hour12: true
    });

    return `${month} ${date.getDate()}, ${date.getFullYear()} at ${time}`;
};

export function getFormattedAge(birthdateString) {
    const birthdate = new Date(birthdateString);
    const today = new Date();

    let age = today.getFullYear() - birthdate.getFullYear();
    const monthDifference = today.getMonth() - birthdate.getMonth();
    const dayDifference = today.getDate() - birthdate.getDate();

    if (monthDifference < 0 || (monthDifference == 0 && dayDifference < 0)) {
        age--;
    }

    return age;
}