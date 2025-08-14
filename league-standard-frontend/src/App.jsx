import NavBar from "./components/UI/NavBar";
import MainSection from "./MainSection";
import { FavoritesProvider } from "./components/UI/UserMenu/FavoritesContext";


function App() {

  return (
    <FavoritesProvider>
      <NavBar />
      <div className="mt-4"></div>
      <MainSection />
    </FavoritesProvider>
  );

}

export default App
