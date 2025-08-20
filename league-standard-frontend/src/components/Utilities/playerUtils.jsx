export function generatePlayerHeadshotPath(player) {
    const sanitizeName = (name) => {
        return name
            .toLowerCase()
            .normalize("NFD")
            .replace(/[\u0300-\u036f]/g, "")
            .replace(/[^a-z0-9]/g, "");
    };

    const first_name = sanitizeName(player.first_name);
    const last_name = sanitizeName(player.last_name);

    const path = "/player-headshots/";

    return path + first_name + "_" + last_name + ".png";
}

export function getFormattedDate(dateString) {
    const date = new Date(dateString);
    const month = date.toLocaleDateString('default', { month: "short" });

    return month + " " + date.getDate() + ", " + date.getFullYear();
}

export function getFormattedDateTime(dateString) {
    const date = new Date(dateString);
    const month = date.toLocaleDateString('default', { month: "short" });
    const time = date.toLocaleString('en-US', {
        hour: 'numeric',
        minute: 'numeric',
        hour12: true
    });

    return month + " " + date.getDate() + ", " + date.getFullYear() + " at " + time;
}