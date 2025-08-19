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
