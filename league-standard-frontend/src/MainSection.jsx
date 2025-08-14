import { BrowserRouter, Routes, Route } from 'react-router-dom';
import TeamsList from './components/Teams/TeamsList';
import GamesList from './components/Games/GamesList';
import FavoritesMenu from './components/UI/UserMenu/FavoritesMenu';
import BookmarksMenu from './components/UI/UserMenu/BookmarksMenu';

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
                                    <Route path="/" element={<GamesList />} />
                                    <Route path="/games" element={<GamesList />} />
                                    <Route path="/teams" element={<TeamsList />} />
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