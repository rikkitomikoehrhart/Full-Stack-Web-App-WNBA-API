import NavBar from "./components/UI/NavBar";
import MainSection from "./MainSection";
import { FavoritesProvider } from "./components/Context/FavoritesContext";
import { TeamColorsProvider } from "./components/Context/TeamColorsContext";


function App() {

  return (
    <FavoritesProvider>
      <TeamColorsProvider>
        <NavBar />
        <div className="mt-4"></div>
        <MainSection />
      </TeamColorsProvider>
    </FavoritesProvider>
  );

}

export default App
