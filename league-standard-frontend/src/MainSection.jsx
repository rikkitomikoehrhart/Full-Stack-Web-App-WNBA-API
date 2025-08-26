import { BrowserRouter, Routes, Route } from 'react-router-dom';
import TeamsList from './components/Teams/TeamsList';
import GamesList from './components/Games/GamesList';
import FavoritesMenu from './components/UI/UserMenu/FavoritesMenu';
import BookmarksMenu from './components/UI/UserMenu/BookmarksMenu';
import Standings from './components/Standings/Standings';
import PlayersList from './components/Players/PlayersList';
import PlayerDetail from './components/Players/PlayerDetail';
import TeamDetail from './components/Teams/TeamDetail';
import NewsList from './components/News/NewsList';
import NewsDetail from './components/News/NewsDetail';
import { ROUTES } from './constants/routes';
import GamesDetail from './components/Games/GamesDetail';

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
                                    <Route path={`${ROUTES.HOME}`} element={<NewsList />} />
                                    <Route path={`${ROUTES.NEWS_DETAIL}`} element={<NewsDetail />} />
                                    <Route path={`${ROUTES.STANDINGS}`} element={<Standings />} />
                                    <Route path={`${ROUTES.GAMES}`} element={<GamesList />} />
                                    <Route path={`${ROUTES.GAMES_DETAIL}`} element={<GamesDetail />} />
                                    <Route path={`${ROUTES.TEAMS}`} element={<TeamsList />} />
                                    <Route path={`${ROUTES.TEAMS_DETAIL}`} element={<TeamDetail />} />
                                    <Route path={`${ROUTES.PLAYERS}`} element={<PlayersList />} />
                                    <Route path={`${ROUTES.PLAYER_DETAIL}`} element={<PlayerDetail />}/>
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