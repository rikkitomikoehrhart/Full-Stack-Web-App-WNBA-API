import NavBar from "./components/UI/NavBar";
import MainSection from "./MainSection";
import { FavoritesProvider } from "./components/Context/FavoritesContext";
import { TeamColorsProvider } from "./components/Context/TeamColorsContext";
import { TeamsProvider } from "./components/Context/TeamsContext";
import { PlayerProvider } from "./components/Context/PlayersContext";


function App() {

  return (
    <TeamsProvider>
      <PlayerProvider>
        <FavoritesProvider>
          <TeamColorsProvider>
            <NavBar />
            <div className="mt-4"></div>
            <MainSection />
          </TeamColorsProvider>
        </FavoritesProvider>
      </PlayerProvider>
    </TeamsProvider>
  );

}

export default App
