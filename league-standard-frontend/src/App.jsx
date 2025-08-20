import NavBar from "./components/UI/NavBar";
import MainSection from "./MainSection";
import { FavoritesProvider } from "./components/Context/FavoritesContext";
import { TeamColorsProvider } from "./components/Context/TeamColorsContext";
import { TeamsProvider } from "./components/Context/TeamsContext";


function App() {

  return (
    <TeamsProvider>
      <FavoritesProvider>
        <TeamColorsProvider>
          <NavBar />
          <div className="mt-4"></div>
          <MainSection />
        </TeamColorsProvider>
      </FavoritesProvider>
    </TeamsProvider>
  );

}

export default App
