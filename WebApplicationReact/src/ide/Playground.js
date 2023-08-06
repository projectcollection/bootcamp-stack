import { useState, useEffect, useRef, useCallback } from "react";
import { Panel, PanelGroup, PanelResizeHandle } from "react-resizable-panels";
import Editor from "@monaco-editor/react";

const Playground = ({
    defaultLayout = [33, 67],
    //TODO:
    //maybe this could be a prop
    problem = {
        description: "some markdown than can be parsed to html",
        tests: []
    },
    onRun = () => { throw new Error("onRun() unimplemented") },
    onSave = () => { throw new Error("onSave() unimplemented") },
}) => {
    const defaultLanguage = "JavaScript (Node.js 12.14.0)";
    const editorRef = useRef(null);

    const [languages, setLanguages] = useState([]);
    const [languageMap, setLanguageMap] = useState();
    const [language, setLanguage] = useState(defaultLanguage);

    const onLayout = (sizes) => {
        document.cookie = `react-resizable-panels:layout=${JSON.stringify(sizes)}`;
    };

    const handleRun = useCallback(() => {
        onRun(editorRef.current?.getValue(), languageMap.get(language));
    }, [editorRef.current, language, languageMap])

    const handleSave = useCallback(() => {
        onSave(editorRef.current?.getValue(), languageMap.get(language));
    }, [editorRef.current, language, languageMap])

    useEffect(() => {
        const getLanguages = async () => {
            const data = await fetch(`${process.env.REACT_APP_JUDGE0_API_URL}/languages`, {
                headers: {
                    "Content-Type": "application/json",
                    'X-RapidAPI-Key': `${process.env.REACT_APP_JUDGE0_RAPID_API_KEY}`
                }
            })
            const languageList = await data.json();

            console.log(data);

            setLanguages(languageList);

            const map = new Map();
            languageList.forEach(language => {
                map.set(language.name, language.id);
            });

            setLanguageMap(map);
        }

        getLanguages();
    }, [])

    return (
        <PanelGroup direction="horizontal">
            <Panel defaultSize={50} minSize={20}>
                <div>
                    <h1>description</h1>
                    <p>
                        {problem.description}
                    </p>
                </div>
            </Panel>
            <PanelResizeHandle
                style={{
                    background: "blue",
                    width: "5px"
                }}
            />
            <Panel minSize={30}>
                <PanelGroup direction="vertical">
                    <Panel defaultSize={50} minSize={20}>
                        <div>
                            <select
                                onChange={(e) => {
                                    setLanguage(e.target.value);
                                }}
                                defaultValue={defaultLanguage}
                            >
                                {languages.map((language) => {
                                    return <option
                                        key={language.id}
                                        selected={language.id === 63}
                                    >
                                        {language.name}
                                    </option>
                                })}
                            </select>

                            <button onClick={handleRun}>
                                run
                            </button>

                            <button onClick={handleSave}>
                                save
                            </button>
                        </div>
                        <Editor
                            defaultLanguage="javascript"
                            language={language}
                            defaultValue="// happy coding"
                            theme="vs-dark"
                            onMount={(editor) => {
                                editorRef.current = editor;
                            }}
                        />
                    </Panel>
                    <PanelResizeHandle
                        style={{
                            background: "blue",
                            height: "5px"
                        }}
                    />
                    <Panel minSize={20}>
                        <h3>tests</h3>
                        <p>
                            {problem.tests.length}
                        </p>
                    </Panel>
                </PanelGroup>
            </Panel>
        </PanelGroup>
    );
}


export default Playground;
