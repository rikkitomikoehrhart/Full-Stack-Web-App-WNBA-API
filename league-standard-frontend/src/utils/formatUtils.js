export function getFormattedHeight(height) {
    const feet = Math.floor(height/12);
    const inches = height % 12;

    return `${feet}" ${inches}'`;
};

export function sanitizeName(name) {
    return name.toLowerCase().normalize("NFD").replace(/[\u0300-\u036f]/g, "").replace(/[^a-z0-9]/g, "");
}

export function truncateText(text, maxLength, suffix = "...") {
    if (text.length <= maxLength) return text;
    return text.slice(0, maxLength) + suffix;
}