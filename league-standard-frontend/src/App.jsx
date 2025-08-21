import { QueryClientProvider } from "@tanstack/react-query";
import { queryClient } from './lib/queryClient';
import NavBar from "./components/UI/NavBar";
import MainSection from "./MainSection";
import { FavoritesProvider } from "./components/Context/FavoritesContext";
import { TeamColorsProvider } from "./components/Context/TeamColorsContext";
import { BookmarksProvider } from "./components/Context/BookmarksContext";


function App() {

  return (

    <QueryClientProvider client={queryClient}>
      <BookmarksProvider>
        <FavoritesProvider>
          <TeamColorsProvider>
            <NavBar />
            <div className="mt-4"></div>
            <MainSection />
          </TeamColorsProvider>
        </FavoritesProvider>
      </BookmarksProvider>
    </QueryClientProvider>


  );

}

export default App
