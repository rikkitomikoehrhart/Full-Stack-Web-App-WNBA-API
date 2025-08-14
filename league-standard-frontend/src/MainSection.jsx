import { BrowserRouter, Routes, Route } from 'react-router-dom';
import TeamsList from './components/Teams/TeamsList';
import GamesList from './components/Games/GamesList';

function MainSection() {
    
    
    return (
        <>
            <div className="container text-center">
                <div className="row">
                    <div className="col-4 d-one d-md-block">

                    </div>

                    <div className="mt-4 col-md-8 col-12">
                        <BrowserRouter>
                            <div id="routerTarget">
                                <Routes>
                                    <Route path="/teams" element={<TeamsList />} />
                                    <Route path="/games" element={<GamesList />} />
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