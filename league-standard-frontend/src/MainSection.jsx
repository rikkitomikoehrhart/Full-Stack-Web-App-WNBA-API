import { BrowserRouter, Routes, Route } from 'react-router-dom';
import TeamsList from './components/Teams/TeamsList';
import GamesList from './components/Games/GamesList';
import FavoritesMenu from './components/UI/UserMenu/FavoritesMenu';
import BookmarksMenu from './components/UI/UserMenu/BookmarksMenu';
import Standings from './components/Standings/Standings';
import PlayersList from './components/Players/PlayersList';

function MainSection() {
    
    
    return (
        <>
            <div className="container text-center">
                <div className="row">
                    <div className="mt-5 col-4 d-none d-md-block">
                        <FavoritesMenu />
                        <BookmarksMenu />
                    </div>

                    <div className="mt-4 col-md-8 col-12">
                        <BrowserRouter>
                            <div id="routerTarget">
                                <Routes>
                                    <Route path="/" element={<Standings />} />
                                    <Route path="/standings" element={<Standings />} />
                                    <Route path="/games" element={<GamesList />} />
                                    <Route path="/teams" element={<TeamsList />} />
                                    <Route path="/players" element={<PlayersList />} />
                                </Routes>
                            </div>
                        </BrowserRouter>
                    </div>
                </div>
            </div>
        </>
    );
}

export default MainSection;